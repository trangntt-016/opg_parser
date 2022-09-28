<h3>OPG - Internal Tool</h3>
</div>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
A tool built during the coop term at Ontario Power Generation (OPG) for admin users. They can pick which domain and types of permission (read/write/read-write/default) they want to grant to regular users. Then the tool uses a backtracking algorithm to read all the (.xsd) files connected to that domain, modify the permission nodes, and create a new (.xsd) file so that users can import and replace it with the old domain file.

![Product Name Screen Shot](image/1.gif)

![Product Name Screen Shot](image/2.gif)

### Built With
* Java 8
* Javascript
* EBX Tibco
* Backtracking algorithm
* JAXB library



## Getting Started

### Installation

* clone the repo
   ```sh
   git clone https://github.com/trangntt-016/opg_parser.git
   ```
* start the project
  ```sh
  cd src/EPLDisplaying
  ```
  ```sh
  cd src/PermissionGenerator
  ```
  ```sh
  Right click on EPLDisplaying.java
  ```
  ```sh
  Right click on PermissionGenerator.java
  ```
