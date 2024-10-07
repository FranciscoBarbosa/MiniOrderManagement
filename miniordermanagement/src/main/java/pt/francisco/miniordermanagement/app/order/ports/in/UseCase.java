package pt.francisco.miniordermanagement.app.order.ports.in;

public interface UseCase <I,O>{

    O execute(I input);
}
