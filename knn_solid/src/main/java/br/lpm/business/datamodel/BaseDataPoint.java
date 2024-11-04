package br.lpm.business.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseDataPoint {
  private List<BaseAttribute> attributes = new ArrayList<>();
  private Object state;

  public void addAttribute(BaseAttribute attribute) {
    attributes.add(attribute);
  }

  public void addAttributes(List<BaseAttribute> attributes) {
    this.attributes.addAll(attributes);
  }

  public BaseAttribute getAtribute(int index) {
    return attributes.get(index);
  }

  public BaseAttribute getAttribute(Object value) {
    return attributes.stream().filter(attribute -> attribute.getValue().equals(value)).findFirst().orElse(null);
  }

  public List<BaseAttribute> getAttributes() {
    return attributes;
  }

  public void removeAttribute(BaseAttribute attribute) {
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

  @SuppressWarnings("unchecked")
  public <T> T getState() {
    return (T) this.state;
  }

  public void setState(Object state) {
    this.state = state;
  }

@Override
public String toString() {
    return "State: " + state + "\nAttributes: " + 
        (attributes != null ? 
            attributes.stream()
                      .map(Object::toString)
                      .collect(Collectors.joining(", ")) 
            : "No attributes available");
}

}
