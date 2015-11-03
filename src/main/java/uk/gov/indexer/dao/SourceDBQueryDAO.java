package uk.gov.indexer.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

public interface SourceDBQueryDAO extends DBConnectionDAO {
    String ENTRIES_TABLE = "entries";

    @RegisterMapper(EntryMapper.class)
    @SqlQuery("select id,entry from " + ENTRIES_TABLE + " where id > :lastreadserialnumber order by id limit 5000")
    List<Entry> read(@Bind("lastReadSerialNumber") int lastReadSerialNumber);
}
