package br.lpm.business.model;

public class Attribute {

  private Object value;

  public Attribute(Object value) {
    this.value = value;
  }

  @SuppressWarnings("unchecked")
  public <T> T getValue() {
    return (T) value;
  }

  public Attribute setValue(Object value) {
    this.value = value;
    return this;
  }

  @Override
  public String toString() {
    return value != null ? value.toString() : "null";
  }

}
