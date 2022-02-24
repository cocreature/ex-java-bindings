package pingpong;

import com.daml.ledger.javaapi.data.*;
import da.internal.template.Archive;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.lang.Deprecated;
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public final class Ping extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("d3a089ecbcc688bbd96ace45dc7d38a000e0423ec4c52d26c47957d9e5fded3d", "PingPong", "Ping");

  public final String sender;

  public final String receiver;

  public final Long count;

  public Ping(String sender, String receiver, Long count) {
    this.sender = sender;
    this.receiver = receiver;
    this.count = count;
  }

  public CreateCommand create() {
    return new CreateCommand(Ping.TEMPLATE_ID, this.toValue());
  }

  public CreateAndExerciseCommand createAndExerciseRespondPong(RespondPong arg) {
    Value argValue = arg.toValue();
    return new CreateAndExerciseCommand(Ping.TEMPLATE_ID, this.toValue(), "RespondPong", argValue);
  }

  public CreateAndExerciseCommand createAndExerciseRespondPong() {
    return createAndExerciseRespondPong(new RespondPong());
  }

  public CreateAndExerciseCommand createAndExerciseArchive(Archive arg) {
    Value argValue = arg.toValue();
    return new CreateAndExerciseCommand(Ping.TEMPLATE_ID, this.toValue(), "Archive", argValue);
  }

  public static CreateCommand create(String sender, String receiver, Long count) {
    return new Ping(sender, receiver, count).create();
  }

  public static Ping fromValue(Value value$) throws IllegalArgumentException {
    Value recordValue$ = value$;
    Record record$ = (Record) recordValue$.asRecord().orElseThrow(() -> new IllegalArgumentException("Contracts must be constructed from Records"));
    @NonNull List<DamlRecord.Field> fields$ = record$.getFields();
    int numberOfFields = fields$.size();
    if (numberOfFields != 3) {
      throw new IllegalArgumentException("Expected 3 arguments, got " + numberOfFields);
    }
    String sender = fields$.get(0).getValue().asParty().orElseThrow(() -> new IllegalArgumentException("Expected sender to be of type com.daml.ledger.javaapi.data.Party")).getValue();
    String receiver = fields$.get(1).getValue().asParty().orElseThrow(() -> new IllegalArgumentException("Expected receiver to be of type com.daml.ledger.javaapi.data.Party")).getValue();
    Long count = fields$.get(2).getValue().asInt64().orElseThrow(() -> new IllegalArgumentException("Expected count to be of type com.daml.ledger.javaapi.data.Int64")).getValue();
    return new pingpong.Ping(sender, receiver, count);
  }

  public Record toValue() {
    ArrayList<Record.Field> fields = new ArrayList<Record.Field>(3);
    fields.add(new Record.Field("sender", new Party(this.sender)));
    fields.add(new Record.Field("receiver", new Party(this.receiver)));
    fields.add(new Record.Field("count", new Int64(this.count)));
    return new Record(fields);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Ping)) {
      return false;
    }
    Ping other = (Ping) object;
    return this.sender.equals(other.sender) && this.receiver.equals(other.receiver) && this.count.equals(other.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.sender, this.receiver, this.count);
  }

  @Override
  public String toString() {
    return String.format("pingpong.Ping(%s, %s, %s)", this.sender, this.receiver, this.count);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Ping> {
    public ContractId(String contractId) {
      super(contractId);
    }

    public ExerciseCommand exerciseRespondPong(RespondPong arg) {
      Value argValue = arg.toValue();
      return new ExerciseCommand(Ping.TEMPLATE_ID, this.contractId, "RespondPong", argValue);
    }

    public ExerciseCommand exerciseRespondPong() {
      return exerciseRespondPong(new RespondPong());
    }

    public ExerciseCommand exerciseArchive(Archive arg) {
      Value argValue = arg.toValue();
      return new ExerciseCommand(Ping.TEMPLATE_ID, this.contractId, "Archive", argValue);
    }
  }

  public static class Contract implements com.daml.ledger.javaapi.data.Contract {
    public final ContractId id;

    public final Ping data;

    public final Optional<String> agreementText;

    public final Set<String> signatories;

    public final Set<String> observers;

    public Contract(ContractId id, Ping data, Optional<String> agreementText,
        Set<String> signatories, Set<String> observers) {
      this.id = id;
      this.data = data;
      this.agreementText = agreementText;
      this.signatories = signatories;
      this.observers = observers;
    }

    public static Contract fromIdAndRecord(String contractId, Record record$,
        Optional<String> agreementText, Set<String> signatories, Set<String> observers) {
      ContractId id = new ContractId(contractId);
      Ping data = Ping.fromValue(record$);
      return new Contract(id, data, agreementText, signatories, observers);
    }

    @Deprecated
    public static Contract fromIdAndRecord(String contractId, Record record$) {
      ContractId id = new ContractId(contractId);
      Ping data = Ping.fromValue(record$);
      return new Contract(id, data, Optional.empty(), Collections.emptySet(), Collections.emptySet());
    }

    public static Contract fromCreatedEvent(CreatedEvent event) {
      return fromIdAndRecord(event.getContractId(), (Record) event.getArguments(), event.getAgreementText(), event.getSignatories(), event.getObservers());
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
        return true;
      }
      if (object == null) {
        return false;
      }
      if (!(object instanceof Contract)) {
        return false;
      }
      Contract other = (Contract) object;
      return this.id.equals(other.id) && this.data.equals(other.data) && this.agreementText.equals(other.agreementText) && this.signatories.equals(other.signatories) && this.observers.equals(other.observers);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.id, this.data, this.agreementText, this.signatories, this.observers);
    }

    @Override
    public String toString() {
      return String.format("pingpong.Ping.Contract(%s, %s, %s, %s, %s)", this.id, this.data, this.agreementText, this.signatories, this.observers);
    }
  }
}
