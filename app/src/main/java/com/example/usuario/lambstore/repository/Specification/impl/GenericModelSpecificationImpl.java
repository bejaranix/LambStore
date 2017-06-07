package com.example.usuario.lambstore.repository.Specification.impl;

import com.example.usuario.lambstore.repository.Specification.ModelSpecification;

/**
 * Implements the standard Specifications according to repository pattern. It generates the standard queries needed for {@link IdModel} objects persistence.
 */

public class GenericModelSpecificationImpl implements ModelSpecification{

    private static final String ALL_QUERY = "SELECT  * FROM %s";

    private static final String SEARCHING_CLAUSE_ID = "id = ?";

    private static final String SEARCHING_CLAUSE = "%s = ?";

    /**
     * Creates the query to get all elements of the table.
     *
     * @param tableName@return {@link String} the query.
     */
    @Override
    public String getAllQuery(String tableName) {
        return String.format(ALL_QUERY, tableName);
    }

    /**
     * Creates the query to select a specific element from database, to update or delete an element, given the id.
     *
     * @param id
     * @return {@link String} the query.
     */
    @Override
    public String getSearchingClauseIdQuery() {
        return SEARCHING_CLAUSE_ID;
    }

    /**
     * Creates the query to select a specific element from database, to select an element given the column and value.
     *
     * @param column
     * @return {@link String} the query.
     */
    @Override
    public String getSearchingClauseQuery(String column) {

        return String.format(SEARCHING_CLAUSE);
    }
}
