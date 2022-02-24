package ghc.stack.types;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.Variant;
import java.lang.String;

public abstract class CallStack {
  public static final String _packageId = "108aa371793a39379d09dd6f02b5613e8bf37ec9893a826747545df5318475f1";

  public CallStack() {
  }

  public abstract Value toValue();

  public static CallStack fromValue(Value value$) {
    Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant ghc.stack.types.CallStack"));
    if ("EmptyCallStack".equals(variant$.getConstructor())) {
      return ghc.stack.types.callstack.EmptyCallStack.fromValue(variant$);
    }
    if ("PushCallStack".equals(variant$.getConstructor())) {
      return ghc.stack.types.callstack.PushCallStack.fromValue(variant$);
    }
    if ("FreezeCallStack".equals(variant$.getConstructor())) {
      return ghc.stack.types.callstack.FreezeCallStack.fromValue(variant$);
    }
    throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant ghc.stack.types.CallStack, expected one of [EmptyCallStack, PushCallStack, FreezeCallStack]");
  }
}
