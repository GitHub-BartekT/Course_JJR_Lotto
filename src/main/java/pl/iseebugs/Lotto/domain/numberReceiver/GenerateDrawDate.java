    package pl.iseebugs.Lotto.domain.numberReceiver;

    import lombok.AllArgsConstructor;
    import lombok.extern.log4j.Log4j2;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import java.time.Clock;
    import java.time.DayOfWeek;
    import java.time.LocalDateTime;
@Log4j2
    class GenerateDrawDate {

        private final Clock clock;

        public GenerateDrawDate(Clock clock) {
            this.clock = clock;
        }

        LocalDateTime generateNextDrawDate(){
            LocalDateTime buyingTicketTime = LocalDateTime.now(clock);
            log.info("generated time now: {}", buyingTicketTime);
            return generateNextDrawDateByDate(buyingTicketTime);
        }

        LocalDateTime generateNextDrawDateByDate(LocalDateTime dateBeforeDrawDate) {
            if(!isSaturday(dateBeforeDrawDate)){
                return goToNextSaturdayNoon(dateBeforeDrawDate);
            }
            if(isBeforeNoon(dateBeforeDrawDate)) {
                return dateBeforeDrawDate.withHour(12).withMinute(0).withSecond(0).withNano(0);
            } else if(isNoon(dateBeforeDrawDate)){
                return dateBeforeDrawDate;
            } else {
                return goToNextSaturdayNoon(dateBeforeDrawDate.plusDays(1));
            }
        }

        private static boolean isBeforeNoon(LocalDateTime dateTime){
            return dateTime.getHour() < 12;
        }

    private static boolean isNoon(LocalDateTime dateTime){
        return  (dateTime.getHour() == 12) && (dateTime.getMinute() == 0) && (dateTime.getSecond() == 0) && (dateTime.getNano() == 0);
    }

        private static boolean isSaturday(LocalDateTime date){
            return date.getDayOfWeek().equals(DayOfWeek.SATURDAY);
        }

        private static LocalDateTime goToNextSaturdayNoon(LocalDateTime date){
            while (date.getDayOfWeek() != DayOfWeek.SATURDAY){
                date = date.plusDays(1);
            }
            return date.withHour(12).withMinute(0).withSecond(0).withNano(0);
        }
    }
