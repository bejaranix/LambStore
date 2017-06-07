package com.example.usuario.lambstore.repository.Specification;

/**
 * Defines the standard Specifications according to repository pattern.
 */
public interface ModelSpecification {

    /**
     * Creates the query to get all elements of the table.
     * @param {@link String}the table name.
     * @return {@link String} the query.
     */
    String getAllQuery(String tableName);

    /**
     * Creates the query to select a specific element from database, to update or delete an element, given the id.
     * @return {@link String} the query.
     */
    String getSearchingClauseIdQuery();

    /**
     * Creates the query to select a specific element from database, to select an element given the column and value.
     * @param {@link String}column, the column of the value.
     * @return {@link String} the query.
     */
    String getSearchingClauseQuery(String column);



}
