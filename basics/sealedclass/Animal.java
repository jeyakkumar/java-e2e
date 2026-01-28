package basics.sealedclass;

public sealed interface Animal permits Dog, Cat, Bird {}
