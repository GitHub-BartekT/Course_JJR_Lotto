package pl.iseebugs.Lotto.domain.resultAnnouncer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryResponseRepositoryTestImpl implements ResponseRepository{
    Map<String, TicketResponse> inMemoryResponseRepository = new HashMap<>();

    @Override
    public <S extends TicketResponse> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    public InMemoryResponseRepositoryTestImpl() {
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
    public void delete(TicketResponse entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends TicketResponse> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<TicketResponse> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TicketResponse> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TicketResponse> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends TicketResponse> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends TicketResponse> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TicketResponse> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TicketResponse> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<TicketResponse> findAll() {
        return null;
    }

    @Override
    public List<TicketResponse> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public <S extends TicketResponse> S save(S entity) {
        inMemoryResponseRepository.put(entity.Id(), entity);
        return entity;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String string) {

    }

    @Override
    public <S extends TicketResponse> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TicketResponse> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TicketResponse> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends TicketResponse, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Optional<TicketResponse> findById(String id) {
        return Optional.ofNullable(inMemoryResponseRepository.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return inMemoryResponseRepository.containsKey(id);
    }
}
