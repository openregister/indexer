package uk.gov.indexer.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

interface TotalRegisterEntriesUpdateDAO extends DBConnectionDAO {
    String TOTAL_REGISTER_ENTRIES_TABLE = "register_entries_count";

    @SqlUpdate("create table if not exists " + TOTAL_REGISTER_ENTRIES_TABLE + " (count integer)")
    void ensureTotalEntriesInRegisterTableExists();

    @SqlUpdate("insert into " + TOTAL_REGISTER_ENTRIES_TABLE + "(count) select 0 where not exists (select 1 from " + TOTAL_REGISTER_ENTRIES_TABLE + ")")
    void initialiseTotalEntriesInRegisterIfRequired();

    @SqlUpdate("update " + TOTAL_REGISTER_ENTRIES_TABLE + " set count=count+:noofentries")
    void increaseTotalEntriesInRegisterCount(@Bind("noOfEntries") int noOfEntries);
}
