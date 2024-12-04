package pt.francisco.miniordermanagement.core.application.port.in;

public interface UseCase<I, O> {

  O execute(I input);
}
