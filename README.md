## Subscription Management System

A telecom-style backend system built with **Spring Boot**, **Aerospike**, **SOAP**,
designed to manage, create **CRUD** operations for user subscriptions, bundles, and operators,
with async bulk creation and activation of subscriptions.
the activation is done using soap.
---

## Features

-  Create, update, and delete **Users**, **Bundles**, and **Operators**
-  Assign Bundles to Users as **Subscriptions**
-  Asynchronous **bulk subscription processing** using kafka or @Async
-  Activate subscriptions through **SOAP API**
-  Aerospike NoSQL database integration for fast performance
