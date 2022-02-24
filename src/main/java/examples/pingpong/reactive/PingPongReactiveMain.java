// Copyright (c) 2020, Digital Asset (Switzerland) GmbH and/or its affiliates. All rights reserved.
// SPDX-License-Identifier: Apache-2.0

package examples.pingpong.reactive;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.rxjava.DamlLedgerClient;
import com.daml.ledger.rxjava.LedgerClient;
import com.daml.ledger.rxjava.PackageClient;
import com.digitalasset.daml_lf_1_8.DamlLf;
import com.digitalasset.daml_lf_1_8.DamlLf1;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ProtocolStringList;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyChannelBuilder;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pingpong.Ping;
import pingpong.Pong;

import javax.net.ssl.SSLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PingPongReactiveMain {

    private final static Logger logger = LoggerFactory.getLogger(PingPongReactiveMain.class);


    // application id used for sending commands
    public static final String APP_ID = "damlhub";

    // constants for referring to the parties
    public static final String ALICE = "Alice";
    public static final String BOB = "Bob";
    public static String tpaPartyAccessToken = "******";
    public static String aliceToken = "******";
    public static String bobToken   = "******";

    public static String alicePartyId = "ledger-party-38f28393-45b6-4740-806a-ffc5d01a8246";// r: "ledger-party-f6f78dd4-0898-4036-a1f8-e4698cae29e2";
    public static String bobPartyId   = "ledger-party-1dc946a0-0398-4d81-8dc0-d5a976096cdb";// r: "ledger-party-74db950a-23f0-4b64-8a17-ee42b7fa9c1d";
    public static String tpaPartyId   = "ledger-party-1252d9b6-9a34-43a9-bfd2-6abdc51d010e";// r: "ledger-party-79995d65-ab91-4e4d-82a0-e5fb8d72b71d";



    public static void main(String[] args) throws SSLException {
        // Extract host and port from arguments
        if (args.length < 2) {
            System.err.println("Usage: HOST PORT [NUM_INITIAL_CONTRACTS]");
            System.exit(-1);
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        // each party will create this number of initial Ping contracts
        int numInitialContracts = args.length == 3 ? Integer.parseInt(args[2]) : 1;

        // create a client object to access services on the ledger
        //DamlLedgerClient client = DamlLedgerClient.newBuilder(host, port).build();
        DamlLedgerClient client = DamlLedgerClient.newBuilder(NettyChannelBuilder.forAddress(host, port).maxInboundMessageSize(90000000)).withSslContext(GrpcSslContexts.forClient().build())
                .withAccessToken(tpaPartyAccessToken).build();

        // Connects to the ledger and runs initial validation
        logger.info("Trying to connect to: {}:{}",host, port);
        client.connect();
        logger.info("Connected!");


        // inspect the packages on the ledger and extract the package id of the package containing the PingPong module
        // this is helpful during development when the package id changes a lot due to frequent changes to the DAML code
        String packageId = detectPingPongPackageId(client);

        logger.info("Found packageId={}", packageId);

        Identifier pingIdentifier = new Identifier(packageId, "PingPong", "Ping");
        Identifier pongIdentifier = new Identifier(packageId, "PingPong", "Pong");

        logger.info("Created two Identifiers for Ping and Pong for module {}", pingIdentifier.getModuleName());

        // initialize the ping pong processors for Alice and Bob
        PingPongProcessor aliceProcessor = new PingPongProcessor(ALICE, client, pingIdentifier, pongIdentifier);
        PingPongProcessor bobProcessor   = new PingPongProcessor(BOB,   client, pingIdentifier, pongIdentifier);

        logger.info("Created two PingPongProcessor for Alice and Bob");

        // start the processors asynchronously
        aliceProcessor.runIndefinitely(Ping.TEMPLATE_ID, alicePartyId, aliceToken);
        bobProcessor.runIndefinitely(Pong.TEMPLATE_ID, bobPartyId, bobToken);

        logger.info("After runIndefinitely for Alice and Bob");


        // send the initial commands for both parties
        createInitialContracts(client, ALICE, BOB, pingIdentifier, numInitialContracts);
        createInitialContracts(client, BOB, ALICE, pongIdentifier, numInitialContracts);

        logger.info("After createInitialContracts for Alice and Bob and vise versa");



        try {
            // wait a couple of seconds for the processing to finish
            Thread.sleep(120000);
            logger.info("Exiting!");
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates numContracts number of Ping contracts. The sender is used as the submitting party.
     *
     * @param client         the {@link LedgerClient} object to use for services
     * @param sender         the party that sends the initial Ping contract
     * @param receiver       the party that receives the initial Ping contract
     * @param identifier the PingPong.Ping examples.pingpong.template identifier
     * @param numContracts   the number of initial contracts to create
     */
    private static void createInitialContracts(LedgerClient client, String sender, String receiver, Identifier identifier, int numContracts) {

        String senderPartyId = "";
        String receiverPartyId = "";
        String commandSubmitterPartyId = "";
        String commandSubmitterToken = "";

        if(sender.equals("Alice")){
            senderPartyId = alicePartyId;
            receiverPartyId = bobPartyId;
            commandSubmitterPartyId = alicePartyId;
            commandSubmitterToken = aliceToken;
        }else if(sender.equals("Bob")){
            senderPartyId = bobPartyId;
            receiverPartyId = alicePartyId;
            commandSubmitterPartyId = bobPartyId;
            commandSubmitterToken = bobToken;
        }

        for (int i = 0; i < numContracts; i++) {
            logger.info("createInitialContracts, contract #{}, sender={}, receiver={}", i, sender, receiver);


            // command that creates the initial Ping contract with the required parameters according to the model
            CreateCommand createCommand = new CreateCommand(identifier,
                    new DamlRecord(
                            identifier,
                            new DamlRecord.Field("sender", new Party(senderPartyId)), // <--------- Here i create the party using its partyId
                            new DamlRecord.Field("receiver", new Party(receiverPartyId)), // <--------- Same here
                            new DamlRecord.Field("count", new Int64(0))
                    )
            );

            // asynchronously send the commands
            client.getCommandClient().submitAndWait(
                    String.format("Ping-%s-%d", sender, i),
                    APP_ID,
                    UUID.randomUUID().toString(),
                    /*sender*/commandSubmitterPartyId,   //<------ HERE i pass the party id like you advised. The party that is the sender which performs the "submit'
                    Collections.singletonList(createCommand),
                    commandSubmitterToken)               //<------ HERE i pass the party's token like you advised
                    .blockingGet();

            logger.info("submitAndWait: {}, Party={}, Command={}", String.format("Ping-%s-%d", sender, i), sender, createCommand.toString());
            logger.info("");
        }

        logger.info("");
        logger.info("");



    }

    /**
     * Inspects all DAML packages that are registered on the ledger and returns the id of the package that contains the PingPong module.
     * This is useful during development when the DAML model changes a lot, so that the package id doesn't need to be updated manually
     * after each change.
     *
     * @param client the initialized client object
     * @return the package id of the example DAML module
     */
    private static String detectPingPongPackageId(LedgerClient client) {
        PackageClient packageService = client.getPackageClient();

        // fetch a list of all package ids available on the ledger
        Flowable<String> packagesIds = packageService.listPackages();

        // fetch all packages and find the package that contains the PingPong module
        String packageId = packagesIds
                .flatMap(p -> packageService.getPackage(p).toFlowable())
                .filter(PingPongReactiveMain::containsPingPongModule)
                .map(GetPackageResponse::getHash)
                .firstElement().blockingGet();

        if (packageId == null) {
            // No package on the ledger contained the PingPong module
            throw new RuntimeException("Module PingPong is not available on the ledger");
        }
        return packageId;
    }

    private static boolean containsPingPongModule(GetPackageResponse getPackageResponse) {
        try {
            // parse the archive payload
            DamlLf.ArchivePayload payload = DamlLf.ArchivePayload.parseFrom(getPackageResponse.getArchivePayload());
            // get the DAML LF package
            DamlLf1.Package lfPackage = payload.getDamlLf1();
            // extract module names
            List<DamlLf1.InternedDottedName> internedDottedNamesList =
                    lfPackage.getInternedDottedNamesList();
            ProtocolStringList internedStringsList = lfPackage.getInternedStringsList();

            for (DamlLf1.Module module : lfPackage.getModulesList()) {
                DamlLf1.DottedName name = null;
                switch (module.getNameCase()) {
                    case NAME_DNAME:
                        name = module.getNameDname();
                        break;
                    case NAME_INTERNED_DNAME:
                        List<Integer> nameIndexes = internedDottedNamesList.get(module.getNameInternedDname()).getSegmentsInternedStrList();
                        List<String> nameSegments = nameIndexes.stream().map(internedStringsList::get).collect(Collectors.toList());
                        name = DamlLf1.DottedName.newBuilder().addAllSegments(nameSegments).build();
                        break;
                    case NAME_NOT_SET:
                        break;
                }
                if (name != null && name.getSegmentsList().size() == 1 && name.getSegmentsList().get(0).equals("PingPong")) {
                    return true;
                }
            }

        } catch (InvalidProtocolBufferException e) {
            logger.error("Error parsing DAML-LF package", e);
            throw new RuntimeException(e);
        }
        return false;
    }
}
