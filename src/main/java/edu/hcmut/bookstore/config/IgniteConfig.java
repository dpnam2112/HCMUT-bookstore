package edu.hcmut.bookstore.config;

import edu.hcmut.bookstore.business.Book;
import edu.hcmut.bookstore.business.SessionInfo;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

import javax.cache.configuration.Factory;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

public class IgniteConfig {
    public static IgniteConfiguration igniteConfig() {
        var cfg = new IgniteConfiguration();

        var bookCacheCfg = new CacheConfiguration<Integer, Book>();
        bookCacheCfg.setName("BookCache");

        var sessionCacheCfg = new CacheConfiguration<Integer, SessionInfo>();
        sessionCacheCfg.setName("SessionCache");

        cfg.setCacheConfiguration(bookCacheCfg, sessionCacheCfg);
        return cfg;
    }
}
