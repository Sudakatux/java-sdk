package com.xapo.tools.widgets;

public class ServiceParameters {

    private String scheme;
    private String host;
    private String path;
    private int port = -1;

    public ServiceParameters(String serviceURL) {
        init(serviceURL);
    }

    private void init(String serviceURL) {
        String[] schemaSplit = serviceURL.split("://");
        this.scheme = schemaSplit[0];
        initPath(schemaSplit[1]);
    }

    private void initPath(String hostAndPath) {
        int slashIndex = hostAndPath.indexOf("/");

        String hostAndPort;
        if (slashIndex < 0) {
            // no slash in URL
            hostAndPort = hostAndPath;
            this.path = "/";
        } else {
            hostAndPort = hostAndPath.substring(0, slashIndex);
            this.path = hostAndPath.substring(slashIndex,
                    hostAndPath.length());
        }

        initHostAndPort(hostAndPort);
    }

    private void initHostAndPort(String hostAndPort) {
        String[] split = hostAndPort.split(":");
        this.host = split[0];

        if (split.length > 1) {
            this.port = Integer.parseInt(split[1]);
        }
    }

    public String getScheme() {
        return scheme;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public int getPort() {
        return port;
    }
}
