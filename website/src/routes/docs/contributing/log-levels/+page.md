---
title: Log Levels
---

### ERROR

To be used when something has gone really wrong, perhaps invalid keys or similar to notify the user/developer.

### WARNING

To be used when something has potentially gone wrong, but is otherwise recoverable. WARNINGS should frequently be reviewed to make sure that we aren't missing errors, or perhaps more elegantly handle recovery, etc.

### INFO

Everything INFO and above is persisted. Statements at this level should be able to provide context for the state of the app and be useful for tracking down bugs or diagnosing WARNING and ERROR messages.

### DEBUG

DEBUG statements are not persisted. This level is only for messages that would be useful to developers running with console logging on.

### CRITICAL

Not currently used.
