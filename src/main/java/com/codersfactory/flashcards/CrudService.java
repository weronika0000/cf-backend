package com.codersfactory.flashcards;

public abstract class CrudService<T extends CrudRepository<A>, A> {
    private final T repository;

    protected CrudService(T repository) {
        this.repository = repository;
    }

    protected final T repository() {
        return repository;
    }

    public A findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No entity with such id"));
    }
}
