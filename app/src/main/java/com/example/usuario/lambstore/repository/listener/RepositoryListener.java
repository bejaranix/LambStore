package com.example.usuario.lambstore.repository.listener;

/**
 * Detects when the repository has changed.
 */
public interface RepositoryListener {

    /**
     * Invoked when the persistence has changed.
     */
    void updateData();

}
