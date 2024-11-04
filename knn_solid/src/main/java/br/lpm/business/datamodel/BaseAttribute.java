package br.lpm.business.datamodel;

import java.util.Optional;

public abstract class BaseAttribute {

  private Object value;

  public BaseAttribute(Object value) {
    this.value = value;
  }

  @SuppressWarnings("unchecked")
  public <T> T getValue() {
    return (T)value;
  }

  public BaseAttribute setValue(Object value) {
    this.value = value;
    return this;
  }

@Override
public String toString() {
    return Optional.ofNullable(value)
                   .map(Object::toString)
                   .orElse("null");
}

}
