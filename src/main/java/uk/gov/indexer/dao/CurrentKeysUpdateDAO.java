package uk.gov.indexer.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.unstable.BindIn;

import java.util.Set;

@UseStringTemplate3StatementLocator
interface CurrentKeysUpdateDAO extends DBConnectionDAO {
    String CURRENT_KEYS_TABLE = "current_keys";

    @SqlUpdate("create table if not exists " + CURRENT_KEYS_TABLE + " (key varchar primary key, serial_number integer unique)")
    void ensureCurrentKeysTableExists();

    @SqlUpdate("update " + CURRENT_KEYS_TABLE + " set serial_number=:serial_number where key=:key")
    int updateSerialNumber(@Bind("serial_number") int serial_number, @Bind("key") String key);

    @SqlQuery("select key from " + CURRENT_KEYS_TABLE + " where key in (<keys>)")
    Set<String> getExistingKeys(@BindIn("keys") Iterable<String> keys);

    @SqlUpdate("insert into " + CURRENT_KEYS_TABLE + "(serial_number, key) values(:serial_number, :key)")
    void insertNewKey(@Bind("serial_number") int serial_number, @Bind("key") String key);
}

