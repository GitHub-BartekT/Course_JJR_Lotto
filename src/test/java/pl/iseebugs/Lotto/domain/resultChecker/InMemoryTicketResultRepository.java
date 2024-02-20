package pl.iseebugs.Lotto.domain.resultChecker;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

class InMemoryTicketResultRepository implements TicketResultRepository {
    Map<String, TicketResult> inMemoryDatabase = new HashMap<>();

    public List<TicketResult> findAllTicketsByDrawDate(LocalDateTime date) {
        return inMemoryDatabase.values()
                .stream()
                .filter(ticketResult -> ticketResult.drawDate().equals(date))
                .toList();
    }

    public InMemoryTicketResultRepository() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public <S extends TicketResult> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends TicketResult> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends TicketResult> List<S> findAll(Example<S> example) {
        return null;
    }



    @Override
    public <S extends TicketResult> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }


    @Override
    public <S extends TicketResult> List<S> saveAll(Iterable<S> entities) {
        Map<String, TicketResult> mapToSave = new HashMap<>();
        for (TicketResult ticket: entities) {
            mapToSave.put(ticket.Id(),ticket);
        }
        inMemoryDatabase.putAll(mapToSave);
        return (List<S>) inMemoryDatabase.values().stream().toList();
    }

    @Override
    public List<TicketResult> findAll() {
        return null;
    }

    @Override
    public List<TicketResult> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public <S extends TicketResult> S save(S entity) {
        return null;
    }

    @Override
    public boolean existsById(String string) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String string) {

    }

    @Override
    public void delete(TicketResult entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends TicketResult> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<TicketResult> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TicketResult> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TicketResult> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TicketResult> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TicketResult> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TicketResult> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends TicketResult, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }


    @Override
    public Optional<TicketResult> findById(String id) {
        return Optional.ofNullable(inMemoryDatabase.get(id));
    }
}
