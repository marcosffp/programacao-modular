package br.lpm.business.datamodel;

public class Attribute extends BaseAttribute {

  public Attribute(Object value) {
    super(value);
  }

  @Override
  public String toString() {
    return "Attribute: " + super.toString();
  }
}
