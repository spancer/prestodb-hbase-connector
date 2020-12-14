package com.facebook.presto.hbase.conf;

import io.airlift.configuration.Config;
import io.airlift.configuration.ConfigDescription;

import javax.validation.constraints.NotNull;

/**
 * HBase catalog config
 * 
 * @author spancer.ray
 *
 */
public class HbaseConfig {
  private String hbaseMaster;
  private String zooKeepers;
  private String zkMetadataRoot = "/presto-hbase";
  private boolean readToInternalTablesEnabled = true;// Can read internal HBase table by default.
  private boolean dropToInternalTablesEnabled = false;// Can't drop internal HBase table by default.

  public String getHbaseMaster() {
    return hbaseMaster;
  }

  @Config("hbase.hosts")
  @ConfigDescription("IP:PORT where hbase master connect")
  public HbaseConfig setHbaseMaster(String hbaseMaster) {
    this.hbaseMaster = hbaseMaster;
    return this;
  }

  @NotNull
  public String getZooKeepers() {
    return this.zooKeepers;
  }

  @Config("hbase.zookeepers")
  @ConfigDescription("ZooKeeper quorum connect string for Hbase")
  public HbaseConfig setZooKeepers(String zooKeepers) {
    this.zooKeepers = zooKeepers;
    return this;
  }

  @NotNull
  public String getZkMetadataRoot() {
    return zkMetadataRoot;
  }

  @Config("hbase.zookeeper.metadata.root")
  @ConfigDescription("Sets the root znode for metadata storage")
  public void setZkMetadataRoot(String zkMetadataRoot) {
    this.zkMetadataRoot = zkMetadataRoot;
  }

  @Config("hbase.internal.table.read.enabled")
  @ConfigDescription("Enable read to non-presto-managed (internal HBase) tables")
  public HbaseConfig setReadToInternalTablesEnabled(boolean readToInternalTablesEnabled) {
    this.readToInternalTablesEnabled = readToInternalTablesEnabled;
    return this;
  }

  @NotNull
  public boolean isReadToInternalTablesEnabled() {
    return readToInternalTablesEnabled;
  }

  @Config("hbase.internal.table.drop.enabled")
  @ConfigDescription("Enable drop to non-presto-managed (internal HBase) tables")
  public HbaseConfig setDropToInternalTablesEnabled(boolean dropToInternalTablesEnabled) {
    this.dropToInternalTablesEnabled = dropToInternalTablesEnabled;
    return this;
  }

  @NotNull
  public boolean isDropToInternalTablesEnabled() {
    return dropToInternalTablesEnabled;
  }


}
