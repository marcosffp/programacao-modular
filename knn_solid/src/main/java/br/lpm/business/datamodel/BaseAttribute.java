package br.lpm.business.datamodel;

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
    return value != null ? value.toString() : "null";
  }
}
