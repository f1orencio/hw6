package com.craftinginterpreters.lox;

import java.util.List;
import java.util.Map;

class LoxClass extends LoxInstance implements LoxCallable {
  final String name;
  final LoxClass superclass;
  private final Map<String, LoxFunction> methods;

  LoxClass(
      LoxClass metaclass,
      String name,
      Map<String, LoxFunction> methods) {
    this(metaclass, name, null, methods);
  }

  LoxClass(
      LoxClass metaclass,
      String name,
      LoxClass superclass,
      Map<String, LoxFunction> methods) {
    super(metaclass);
    this.name = name;
    this.superclass = superclass;
    this.methods = methods;
  }

  LoxFunction findMethod(LoxInstance instance, String name) {
    LoxFunction method = null;
    LoxFunction inner = null;
    LoxClass klass = this;

    while (klass != null) {
      if (klass.methods.containsKey(name)) {
        inner = method;
        method = klass.methods.get(name);
      }

      klass = klass.superclass;
    }

    if (method != null) {
      return method.bind(instance, inner);
    }

    return null;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public Object call(
      Interpreter interpreter,
      List<Object> arguments) {
    LoxInstance instance = new LoxInstance(this);

    LoxFunction initializer = findMethod(instance, "init");

    if (initializer != null) {
      initializer.call(interpreter, arguments);
    }

    return instance;
  }

  @Override
  public int arity() {
    // The instance is only needed to bind a method.
    // Initializer arity depends on its parameters.
    LoxFunction initializer = findUnboundMethod("init");
    return initializer == null ? 0 : initializer.arity();
  }

  private LoxFunction findUnboundMethod(String name) {
    LoxFunction method = null;
    LoxClass klass = this;

    while (klass != null) {
      if (klass.methods.containsKey(name)) {
        method = klass.methods.get(name);
      }
      klass = klass.superclass;
    }

    return method;
  }
}