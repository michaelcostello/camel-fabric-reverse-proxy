## Camel reverse proxy with Fuse Fabric

### How to install

This uses the fabric8 plugin to generate a profile to install into a fabric.

See more here: http://fabric8.io/#/site/book/doc/index.md?chapter=mavenPlugin_md


Note, to use the fabric8-maven-plugin, you'll need this in your ~/.m2/settings.xml (see the docs):

<server>
  <id>fabric8.upload.repo</id>
  <username>admin</username>
  <password>admin</password>
</server>


Run:
    mvn fabric8:deploy
    
to run against a local fabric. if you have a remote fabric do this:

    mvn fabric8:deploy -Dfabric8.jolokiaUrl=http://fuse-xpaas.xpaas.com/jolokia
    
    
This should build the jar, build the profile, upload both to the fabric, and from there you
can deploy it to a container.


The routing is limited, although the pieces are in place to expand on it.

The assumption is that each group of webservices to load balance against has one and only one
url contet path (for e.g., /context/path/foo)

What this does is take a request, determine how to map the path to the fabric cluster name and then
call it using a recipient list.

For example, a call to http://localhost:9000/test will use the recipient list to call fabric:gatewaytest

Note that the context was used to concatenate with a constant "gateway" ... so /test got replaced to gatewaytest.
See the Router class for the implementation of the recipient list. Also note where you can plug in your custom logic.

There are three groups: test, beer, cheese.

You can hit each one with the following commands:


    curl http://localhost:9000/beer
    curl http://localhost:9000/test
    curl http://localhost:9000/cheese
    
    
Note, there is a small bug in the fabric lookup code that might fail for you on the first attempt.

It this is the case please let me know. I'm going to open a JIRA for it anyway, but still curiuos how many
people it affects.


Good Luck!