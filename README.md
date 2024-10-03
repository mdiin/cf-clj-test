# CloudFoundry Clojure Test

This repo is an example of a minimal Clojure app that packaged is packaged as an uberjar using tools.build. It runs to completion when executed locally using `java -jar`, but crashes when run on CloudFoundry.

Since obtaining access to a CloudFoundry instance might not be easy, this README contains the resulting stacktrace as well as the run output from CloudFoundry.

## Output of running the app

This is the summary output from requesting a CloudFoundry run (see [Stacktrace](#stacktrace) below for error details):

```
name:                clj-test
requested state:     started
isolation segment:   trial
routes:              clj-test.cfapps.us10-001.hana.ondemand.com
last uploaded:       Thu 03 Oct 07:55:42 CEST 2024
stack:               cflinuxfs4
buildpacks:
isolation segment:   trial
        name                                                 version                                                              detect output   buildpack name
        https://github.com/cloudfoundry/java-buildpack.git   e2dd4eb-https://github.com/cloudfoundry/java-buildpack.git#e2dd4eb   java            java

type:            web
sidecars:
instances:       0/1
memory usage:    1024M
start command:   JAVA_OPTS="-agentpath:$PWD/.java-buildpack/open_jdk_jre/bin/jvmkill-1.17.0_RELEASE=printHeapHistogram=1 -Djava.io.tmpdir=$TMPDIR
                 -XX:ActiveProcessorCount=$(nproc)
                 -Djava.ext.dirs=$PWD/.java-buildpack/container_security_provider:$PWD/.java-buildpack/open_jdk_jre/lib/ext
                 -Djava.security.properties=$PWD/.java-buildpack/java_security/java.security $JAVA_OPTS" &&
                 CALCULATED_MEMORY=$($PWD/.java-buildpack/open_jdk_jre/bin/java-buildpack-memory-calculator-3.13.0_RELEASE -totMemory=$MEMORY_LIMIT
                 -loadedClasses=10276 -poolType=metaspace -stackThreads=250 -vmOptions="$JAVA_OPTS") && echo JVM Memory Configuration: $CALCULATED_MEMORY
                 && JAVA_OPTS="$JAVA_OPTS $CALCULATED_MEMORY" && MALLOC_ARENA_MAX=2 eval exec $PWD/.java-buildpack/open_jdk_jre/bin/java $JAVA_OPTS -cp
                 $PWD/.:$PWD/.java-buildpack/client_certificate_mapper/client_certificate_mapper-2.0.1.jar cf.clj.main
     state     since                  cpu    memory     disk       logging        cpu entitlement   details
#0   crashed   2024-10-03T05:56:22Z   0.0%   0B of 0B   0B of 0B   0B/s of 0B/s   0.0%

type:            task
sidecars:
instances:       0/0
memory usage:    1024M
start command:   JAVA_OPTS="-agentpath:$PWD/.java-buildpack/open_jdk_jre/bin/jvmkill-1.17.0_RELEASE=printHeapHistogram=1 -Djava.io.tmpdir=$TMPDIR
                 -XX:ActiveProcessorCount=$(nproc)
                 -Djava.ext.dirs=$PWD/.java-buildpack/container_security_provider:$PWD/.java-buildpack/open_jdk_jre/lib/ext
                 -Djava.security.properties=$PWD/.java-buildpack/java_security/java.security $JAVA_OPTS" &&
                 CALCULATED_MEMORY=$($PWD/.java-buildpack/open_jdk_jre/bin/java-buildpack-memory-calculator-3.13.0_RELEASE -totMemory=$MEMORY_LIMIT
                 -loadedClasses=10276 -poolType=metaspace -stackThreads=250 -vmOptions="$JAVA_OPTS") && echo JVM Memory Configuration: $CALCULATED_MEMORY
                 && JAVA_OPTS="$JAVA_OPTS $CALCULATED_MEMORY" && MALLOC_ARENA_MAX=2 eval exec $PWD/.java-buildpack/open_jdk_jre/bin/java $JAVA_OPTS -cp
                 $PWD/.:$PWD/.java-buildpack/client_certificate_mapper/client_certificate_mapper-2.0.1.jar cf.clj.main
There are no running instances of this process.
Start unsuccessful
```

## Stacktrace

This is the part of the run output that contains the stacktrace. The create and teardown logs from CloudFoundry are included for completeness:

```
   2024-10-03T07:53:49.45+0200 [CELL/0] OUT Cell c839c7b1-088e-41af-b9b7-66256aacd457 creating container for instance 0081f869-1d95-44da-4eee-83ce
   2024-10-03T07:53:49.77+0200 [CELL/0] OUT Security group rules were updated
   2024-10-03T07:53:49.82+0200 [CELL/0] OUT Cell c839c7b1-088e-41af-b9b7-66256aacd457 successfully created container for instance 0081f869-1d95-44da-4eee-83ce
   2024-10-03T07:53:50.02+0200 [CELL/0] OUT Downloading droplet...
   2024-10-03T07:53:50.52+0200 [CELL/0] OUT Downloaded droplet
   2024-10-03T07:53:50.52+0200 [CELL/0] OUT Starting health monitoring of container
   2024-10-03T07:53:50.57+0200 [APP/PROC/WEB/0] OUT Invoking pre-start scripts.
   2024-10-03T07:53:50.58+0200 [APP/PROC/WEB/0] OUT Invoking start command.
   2024-10-03T07:53:50.59+0200 [APP/PROC/WEB/0] OUT JVM Memory Configuration: -Xmx464700K -Xss1M -XX:ReservedCodeCacheSize=240M -XX:MaxDirectMemorySize=10M -XX:MaxMetaspaceSize=71875K
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR Exception in thread "main" java.lang.ExceptionInInitializerError
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at java.lang.Class.forName0(Native Method)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at java.lang.Class.forName(Class.java:348)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.classForName(RT.java:2209)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.classForName(RT.java:2218)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.loadClassForName(RT.java:2237)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.load(RT.java:449)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.load(RT.java:424)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load$fn__1742.invoke(core.clj:6162)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load.invokeStatic(core.clj:6161)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load.doInvoke(core.clj:6145)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RestFn.invoke(RestFn.java:408)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_one.invokeStatic(core.clj:5934)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_one.invoke(core.clj:5929)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_lib$fn__1684.invoke(core.clj:5976)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_lib.invokeStatic(core.clj:5975)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_lib.doInvoke(core.clj:5954)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RestFn.applyTo(RestFn.java:142)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$apply.invokeStatic(core.clj:669)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$apply.invoke(core.clj:662)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_libs.invokeStatic(core.clj:6018)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_libs.doInvoke(core.clj:6001)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RestFn.applyTo(RestFn.java:137)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$apply.invokeStatic(core.clj:669)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$require.invokeStatic(core.clj:6039)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core.server$loading__6789__auto____8961.invoke(server.clj:9)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core.server__init.load(Unknown Source)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core.server__init.<clinit>(Unknown Source)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at java.lang.Class.forName0(Native Method)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at java.lang.Class.forName(Class.java:348)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.classForName(RT.java:2209)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.classForName(RT.java:2218)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.loadClassForName(RT.java:2237)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.load(RT.java:449)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.load(RT.java:424)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load$fn__1742.invoke(core.clj:6162)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load.invokeStatic(core.clj:6161)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load.doInvoke(core.clj:6145)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RestFn.invoke(RestFn.java:408)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_one.invokeStatic(core.clj:5934)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_one.invoke(core.clj:5929)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_lib$fn__1684.invoke(core.clj:5976)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_lib.invokeStatic(core.clj:5975)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_lib.doInvoke(core.clj:5954)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RestFn.applyTo(RestFn.java:142)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$apply.invokeStatic(core.clj:669)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$apply.invoke(core.clj:662)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_libs.invokeStatic(core.clj:6018)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_libs.doInvoke(core.clj:6001)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RestFn.applyTo(RestFn.java:137)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$apply.invokeStatic(core.clj:669)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$apply.invoke(core.clj:662)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$require.invokeStatic(core.clj:6107)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.core$require.doInvoke(core.clj:6039)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RestFn.invoke(RestFn.java:408)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.Var.invoke(Var.java:384)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.doInit(RT.java:491)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.init(RT.java:467)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at clojure.lang.Util.loadWithClass(Util.java:248)
   2024-10-03T07:53:53.67+0200 [APP/PROC/WEB/0] ERR at cf.clj.main.<clinit>(Unknown Source)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR Caused by: Syntax error macroexpanding clojure.core/defn at (clojure/spec/alpha.clj:85:1).
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.Compiler.checkSpecs(Compiler.java:6989)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.Compiler.macroexpand1(Compiler.java:7005)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.Compiler.macroexpand(Compiler.java:7092)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.Compiler.eval(Compiler.java:7178)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.Compiler.load(Compiler.java:7653)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.loadResourceScript(RT.java:381)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.loadResourceScript(RT.java:372)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.load(RT.java:459)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.load(RT.java:424)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$load$fn__1742.invoke(core.clj:6162)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$load.invokeStatic(core.clj:6161)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$load.doInvoke(core.clj:6145)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RestFn.invoke(RestFn.java:408)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_one.invokeStatic(core.clj:5934)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_one.invoke(core.clj:5929)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_lib$fn__1684.invoke(core.clj:5976)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_lib.invokeStatic(core.clj:5975)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_lib.doInvoke(core.clj:5954)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RestFn.applyTo(RestFn.java:142)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$apply.invokeStatic(core.clj:669)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$apply.invoke(core.clj:662)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_libs.invokeStatic(core.clj:6018)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$load_libs.doInvoke(core.clj:6001)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RestFn.applyTo(RestFn.java:137)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$apply.invokeStatic(core.clj:669)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$require.invokeStatic(core.clj:6039)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.main$loading__6789__auto____9094.invoke(main.clj:11)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.main__init.load(Unknown Source)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.main__init.<clinit>(Unknown Source)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR ... 59 more
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR Caused by: java.lang.Exception: #object[clojure.spec.alpha$and_spec_impl$reify__3184 0x534e58b6 "clojure.spec.alpha$and_spec_impl$reify__3184@534e58b6"] is not a fn, expected predicate fn
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$dt.invokeStatic(alpha.clj:769)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$dt.invoke(alpha.clj:759)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$dt.invokeStatic(alpha.clj:760)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$dt.invoke(alpha.clj:759)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invokeStatic(alpha.clj:1534)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invoke(alpha.clj:1528)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invokeStatic(alpha.clj:1542)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invoke(alpha.clj:1528)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv$fn__3426.invoke(alpha.clj:1544)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$map$fn__603.invoke(core.clj:2772)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.LazySeq.sval(LazySeq.java:42)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.LazySeq.seq(LazySeq.java:51)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.seq(RT.java:535)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$seq__27.invokeStatic(core.clj:139)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$seq__27.invoke(core.clj:139)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$map$fn__610.invoke(core.clj:2781)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.LazySeq.sval(LazySeq.java:42)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.LazySeq.seq(LazySeq.java:51)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.seq(RT.java:535)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$seq__27.invokeStatic(core.clj:139)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$seq__27.invoke(core.clj:139)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$filter$fn__634.invoke(core.clj:2827)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.LazySeq.sval(LazySeq.java:42)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.LazySeq.seq(LazySeq.java:51)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.seq(RT.java:535)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$seq__27.invokeStatic(core.clj:139)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$seq__27.invoke(core.clj:139)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$map$fn__603.invoke(core.clj:2764)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.LazySeq.sval(LazySeq.java:42)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.LazySeq.seq(LazySeq.java:51)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.RT.seq(RT.java:535)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$seq__27.invokeStatic(core.clj:139)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.core$seq__27.invoke(core.clj:139)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$filter_alt.invokeStatic(alpha.clj:1431)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$filter_alt.invoke(alpha.clj:1425)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$alt_STAR_.invokeStatic(alpha.clj:1435)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$alt_STAR_.invoke(alpha.clj:1434)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invokeStatic(alpha.clj:1544)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invoke(alpha.clj:1528)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invokeStatic(alpha.clj:1542)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invoke(alpha.clj:1528)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invokeStatic(alpha.clj:1543)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invoke(alpha.clj:1528)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invokeStatic(alpha.clj:1543)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$deriv.invoke(alpha.clj:1528)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$re_conform.invokeStatic(alpha.clj:1669)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$re_conform.invoke(alpha.clj:1660)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$regex_spec_impl$reify__3510.conform_STAR_(alpha.clj:1710)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$conform.invokeStatic(alpha.clj:171)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$conform.invoke(alpha.clj:167)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$macroexpand_check.invokeStatic(alpha.clj:708)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.spec.alpha$macroexpand_check.invoke(alpha.clj:704)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.AFn.applyToHelper(AFn.java:156)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.AFn.applyTo(AFn.java:144)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.Var.applyTo(Var.java:705)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR at clojure.lang.Compiler.checkSpecs(Compiler.java:6987)
   2024-10-03T07:53:53.68+0200 [APP/PROC/WEB/0] ERR ... 87 more
   2024-10-03T07:53:53.73+0200 [APP/PROC/WEB/0] OUT Exit status 1
   2024-10-03T07:53:59.54+0200 [CELL/0] OUT Cell c839c7b1-088e-41af-b9b7-66256aacd457 stopping instance 0081f869-1d95-44da-4eee-83ce
   2024-10-03T07:53:59.54+0200 [CELL/0] OUT Cell c839c7b1-088e-41af-b9b7-66256aacd457 destroying container for instance 0081f869-1d95-44da-4eee-83ce
   2024-10-03T07:53:59.56+0200 [API/12] OUT Process has crashed with type: "web"
   2024-10-03T07:53:59.57+0200 [API/12] OUT App instance exited with guid a214cc3c-ca2f-4698-b99e-2662cf3a5a5e payload: {"instance"=>"0081f869-1d95-44da-4eee-83ce", "index"=>0, "cell_id"=>"c839c7b1-088e-41af-b9b7-66256aacd457", "reason"=>"CRASHED", "exit_description"=>"APP/PROC/WEB: Exited with status 1", "crash_count"=>4, "crash_timestamp"=>1727934839547354629, "version"=>"bbf21aa0-1766-4ffc-a3b1-b91a72081cd8"}
   2024-10-03T07:53:59.65+0200 [PROXY/0] OUT Exit status 137
   2024-10-03T07:54:00.48+0200 [CELL/0] OUT Cell c839c7b1-088e-41af-b9b7-66256aacd457 successfully destroyed container for instance 0081f869-1d95-44da-4eee-83ce
   2024-10-03T07:54:04.13+0200 [API/19] OUT Updated app with guid a214cc3c-ca2f-4698-b99e-2662cf3a5a5e ({"environment_variables"=>"[PRIVATE DATA HIDDEN]"})
```

## Obtaining a CloudFoundry test environment

SAP BTP is a CloudFoundry-based application platform where it is possible to register for a trial account: https://hanatrial.ondemand.com/

## CloudFoundry introduction

There is a tutorial for developers on the CloudFoundry site: https://tutorials.cloudfoundry.org/cf4devs/introduction/
