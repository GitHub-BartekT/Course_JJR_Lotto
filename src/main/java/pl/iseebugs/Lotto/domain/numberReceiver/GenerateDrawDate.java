    package pl.iseebugs.Lotto.domain.numberReceiver;

    import lombok.AllArgsConstructor;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import java.time.Clock;
    import java.time.DayOfWeek;
    import java.time.LocalDateTime;

    @AllArgsConstructor
    class GenerateDrawDate {

        private final Clock clock;

        LocalDateTime generateNextDrawDate(){
            LocalDateTime buyingTicketTime = LocalDateTime.now(clock);
            return generateNextDrawDateByDate(buyingTicketTime);
        }

        LocalDateTime generateNextDrawDateByDate(LocalDateTime dateBeforeDrawDate) {
            if(!isSaturday(dateBeforeDrawDate)){
                return goToNextSaturdayNoon(dateBeforeDrawDate);
            }

            if(isBeforeNoon(dateBeforeDrawDate)) {
                return dateBeforeDrawDate.withHour(12).withMinute(0).withSecond(0).withNano(0);
            } else {
                return goToNextSaturdayNoon(dateBeforeDrawDate.plusDays(1));
            }
        }

        private static boolean isBeforeNoon(LocalDateTime dateTime){
            return dateTime.getHour() < 12;
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
