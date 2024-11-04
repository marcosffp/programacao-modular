package br.lpm.business.datamodel;

public class SimpleAttribute extends BaseAttribute {

  public SimpleAttribute(Object value) {
    super(value);
  }

  @Override
  public String toString() {
    return "Attribute: " + super.toString();
  }
}
