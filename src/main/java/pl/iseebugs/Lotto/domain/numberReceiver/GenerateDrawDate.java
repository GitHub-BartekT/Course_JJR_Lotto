    package pl.iseebugs.Lotto.domain.numberReceiver;

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import java.time.DayOfWeek;
    import java.time.LocalDateTime;


    class GenerateDrawDate {

        private static final Logger logger = LoggerFactory.getLogger(GenerateDrawDate.class);
        static LocalDateTime generateNextDrawDate (LocalDateTime buyingTicketTime){
            if(!isSaturday(buyingTicketTime)){
                return goToNextSaturday(buyingTicketTime).withHour(12).withMinute(0).withSecond(0).withNano(0);
            }

            if(isBeforeNoon(buyingTicketTime)) {
                return buyingTicketTime.withHour(12).withMinute(0).withSecond(0).withNano(0);
            } else {
                return goToNextSaturday(buyingTicketTime.plusDays(1)).withHour(12).withMinute(0).withSecond(0).withNano(0);
            }
        }

        private static boolean isBeforeNoon(LocalDateTime dateTime){
            return dateTime.getHour() < 12;
        }

        private static boolean isSaturday(LocalDateTime date){
            return date.getDayOfWeek().equals(DayOfWeek.SATURDAY);
        }

        private static LocalDateTime goToNextSaturday(LocalDateTime date){
            while (date.getDayOfWeek() != DayOfWeek.SATURDAY){
                date = date.plusDays(1);
            }
            return date;
        }
    }
