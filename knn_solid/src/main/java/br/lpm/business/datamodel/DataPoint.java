package br.lpm.business.datamodel;

import java.util.ArrayList;
import java.util.List;


public class DataPoint {
  private List<Attribute> attributes = new ArrayList<>();
  private Object state;

  public void addAttribute(Attribute attribute) {
    attributes.add(attribute);
  }

  public List<Attribute> getAttributes() {
    return attributes;
  }

  @SuppressWarnings("unchecked")
  public <T> T getState() {
    return (T) this.state;
  }

  public void setState(Object state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "State: " + state + "\nAttributes: " + attributes;
  }
}
