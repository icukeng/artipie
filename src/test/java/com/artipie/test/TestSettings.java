/*
 * The MIT License (MIT) Copyright (c) 2020-2021 artipie.com
 * https://github.com/artipie/artipie/LICENSE.txt
 */
package com.artipie.test;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.artipie.api.ssl.KeyStore;
import com.artipie.asto.Storage;
import com.artipie.asto.memory.InMemoryStorage;
import com.artipie.settings.ArtipieSecurity;
import com.artipie.settings.MetricsContext;
import com.artipie.settings.Settings;
import com.artipie.settings.cache.ArtipieCaches;
import java.util.Optional;

/**
 * Test {@link Settings} implementation.
 *
 * @since 0.2
 */
@SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
public final class TestSettings implements Settings {

    /**
     * Storage.
     */
    private final Storage storage;

    /**
     * Yaml `meta` mapping.
     */
    private final YamlMapping meta;

    /**
     * KeyStore.
     */
    private final Optional<KeyStore> keystore;

    /**
     * Test caches.
     */
    private final ArtipieCaches caches;

    /**
     * Ctor.
     */
    public TestSettings() {
        this(new InMemoryStorage());
    }

    /**
     * Ctor.
     *
     * @param storage Storage
     */
    public TestSettings(final Storage storage) {
        this(
            storage,
            Yaml.createYamlMappingBuilder().build()
        );
    }

    /**
     * Ctor.
     *
     * @param meta Yaml `meta` mapping
     */
    public TestSettings(final YamlMapping meta) {
        this(new InMemoryStorage(), meta);
    }

    /**
     * Primary ctor.
     *
     * @param storage Storage
     * @param meta Yaml `meta` mapping
     * @checkstyle ParameterNumberCheck (2 lines)
     */
    public TestSettings(final Storage storage, final YamlMapping meta) {
        this(storage, meta, Optional.empty());
    }

    /**
     * Primary ctor.
     *
     * @param storage Storage
     * @param meta Yaml `meta` mapping
     * @param keystore KeyStore
     * @checkstyle ParameterNumberCheck (2 lines)
     */
    public TestSettings(
        final Storage storage,
        final YamlMapping meta,
        final Optional<KeyStore> keystore
    ) {
        this.storage = storage;
        this.meta = meta;
        this.keystore = keystore;
        this.caches = new TestArtipieCaches();
    }

    @Override
    public Storage configStorage() {
        return this.storage;
    }

    @Override
    public ArtipieSecurity authz() {
        return null;
    }

    @Override
    public YamlMapping meta() {
        return this.meta;
    }

    @Override
    public Storage repoConfigsStorage() {
        return this.storage;
    }

    @Override
    public Optional<KeyStore> keyStore() {
        return this.keystore;
    }

    @Override
    public MetricsContext metrics() {
        return new MetricsContext(Yaml.createYamlMappingBuilder().build());
    }

    @Override
    public ArtipieCaches caches() {
        return this.caches;
    }
}