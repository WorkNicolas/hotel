import reservation.ReservationState;

public class ReservationStatus extends Reactive<ReservationState>{
    public static ReservationStatus self = new ReservationStatus(ReservationState.NONE);

    public ReservationStatus(ReservationState value) {
        super(value);
    }
}