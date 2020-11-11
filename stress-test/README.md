# Gatling Load Test

#### Needed dependencies

- JDK 8
- To run this test you need to have the **SBT** tool installed, you can download and find the instructions to install on this [link](https://www.scala-sbt.org/download.html)

#### Purpose

- The purpose of this test is to verify the load capacity and performance on the ***VOTE-API***, which in theory is the one that will suffer the greatest load due to the number of simultaneous associateds who can vote.
- The tested scenario is: "*a session was opened and 100 members vote at the same time, the service need to process all votes with success*".

#### Preparing the scenario and testing

- The service is assumed to be **UP** and **RUNNING**.
- To create the appropriate scenario for the load test, follow the instructions below:

1. Create an agenda on the endpoint *v1/agendas*.
    - The test uses the **agendaId = 1**, so if you've already created an agenda, skip this part.
2. Open a voting session with a duration of at least 2 minutes for the **agendaId = 1** on the endpoint *v1/sessions/agenda/1/open*.
2. Now you can run the Gatling test by entering the following command ```sbt gatling:test``` on the main folder.
3. To see the results of the load test, open the file `{stress_test_folder}/target/gatling/assemblysimulation-{timestamp}/index.html`.