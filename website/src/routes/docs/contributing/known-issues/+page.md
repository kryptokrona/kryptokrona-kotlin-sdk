---
title: Known Issues
---

There are some issues that can occur while working on this code base. Here we list some of them and what to do to circumvent them.

## Unit Testing fails with validity check

If you get a similar output as below, the SSL certificate of the node have most likely expired.

You can check if it has expired with:

```
openssl s_client -connect <domain>:<port> -showcerts
```

Please contact the node operator if that's the case to solve it.

```
validity check failed
java.security.cert.CertPathValidatorException: validity check failed
	at java.base/sun.security.provider.certpath.PKIXMasterCertPathValidator.validate(PKIXMasterCertPathValidator.java:135)
	at java.base/sun.security.provider.certpath.PKIXCertPathValidator.validate(PKIXCertPathValidator.java:224)
	at java.base/sun.security.provider.certpath.PKIXCertPathValidator.validate(PKIXCertPathValidator.java:144)
	at java.base/sun.security.provider.certpath.PKIXCertPathValidator.engineValidate(PKIXCertPathValidator.java:83)
	at java.base/java.security.cert.CertPathValidator.validate(CertPathValidator.java:309)
	at java.base/sun.security.validator.PKIXValidator.doValidate(PKIXValidator.java:364)
	at java.base/sun.security.validator.PKIXValidator.engineValidate(PKIXValidator.java:263)
	at java.base/sun.security.validator.Validator.validate(Validator.java:264)
	at java.base/sun.security.ssl.X509TrustManagerImpl.checkTrusted(X509TrustManagerImpl.java:242)
	at java.base/sun.security.ssl.X509TrustManagerImpl.checkServerTrusted(X509TrustManagerImpl.java:113)
	at io.ktor.network.tls.TLSClientHandshake.handleCertificatesAndKeys(TLSClientHandshake.kt:233)
	at io.ktor.network.tls.TLSClientHandshake.access$handleCertificatesAndKeys(TLSClientHandshake.kt:24)
	at io.ktor.network.tls.TLSClientHandshake$handleCertificatesAndKeys$1.invokeSuspend(TLSClientHandshake.kt)
	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:108)
	at kotlinx.coroutines.internal.LimitedDispatcher$Worker.run(LimitedDispatcher.kt:115)
	at kotlinx.coroutines.scheduling.TaskImpl.run(Tasks.kt:103)
	at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:584)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:793)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:697)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:684)
```