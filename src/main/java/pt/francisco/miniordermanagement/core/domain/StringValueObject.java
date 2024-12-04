package pt.francisco.miniordermanagement.core.domain;

import java.util.Objects;

public abstract class StringValueObject {
  private final String value;

  protected StringValueObject(String value) {
    this.value = value;
  }

  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj == null) {
      return false;
    } else if (this.getClass() != obj.getClass()) {
      return false;
    } else {
      StringValueObject other = (StringValueObject) obj;
      return Objects.equals(this.value, other.value);
    }
  }

  public int hashCode() {
    return Objects.hashCode(this.value);
  }
}
