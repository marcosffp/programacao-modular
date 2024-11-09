package br.lpm.data_structures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataPoint {
  private List<Attribute> attributes = new ArrayList<>();
  private Attribute state;

  public void addAttribute(Attribute attribute) {
    attributes.add(attribute);
  }

  public void addAttributes(List<Attribute> attributes) {
    this.attributes.addAll(attributes);
  }

  public Attribute getAtribute(int index) {
    return attributes.get(index);
  }

  public Attribute getAttribute(Object value) {
    return attributes.stream().filter(attribute -> attribute.getValue().equals(value)).findFirst().orElse(null);
  }

  public List<Attribute> getAttributes() {
    return attributes;
  }

  public void removeAttribute(Attribute attribute) {
    attributes.remove(attribute);
  }

  public void removeAttribute(Object value) {
    attributes.removeIf(attribute -> attribute.getValue().equals(value));
  }

  public void removeAttribute(int index) {
    attributes.remove(index);
  }

  public void clearAttributes() {
    attributes.clear();
  }

  public int sizeAttribute() {
    return attributes.size();
  }

  public Attribute getState() {
    return state;
  }

  public void setState(Attribute state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return attributes.stream()
        .map(attribute -> {
          Object value = attribute.getValue();
          return value != null ? value.toString() : "null";
        })
        .collect(Collectors.joining(", ", "[", "]"));
  }
}
