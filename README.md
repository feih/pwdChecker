# Password Checker

Built based on SpringBoot and provides a RESTFUL service:

> http://localhost:8080/checkpwd?input=abcjef123
```
{
  serviceName: "PasswordChecker",
  serviceResult: "passed",
  success: true
}
```

The service checks against the following rules:
> -Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.

> -Must be between 5 and 12 characters in length.

> -Must not contain any sequence of characters immediately followed by the same sequence.


Copyright (c) 2016
