# Shopizer 3.X (for java 1.11 +) (tested with Java 11, 16 and 17)
# Created By Shamshad
> [!NOTE]
> The team is working on an upcoming efficient microservices version. Stay tuned !

[![last_version](https://img.shields.io/badge/last_version-v3.2.5-blue.svg?style=flat)](https://github.com/shopizer-ecommerce/shopizer/tree/3.2.5)
[![Official site](https://img.shields.io/website-up-down-green-red/https/shields.io.svg?label=official%20site)](http://www.shopizer.com/)
[![Docker Pulls](https://img.shields.io/docker/pulls/shopizerecomm/shopizer.svg)](https://hub.docker.com/r/shopizerecomm/shopizer)
[![stackoverflow](https://img.shields.io/badge/shopizer-stackoverflow-orange.svg?style=flat)](http://stackoverflow.com/questions/tagged/shopizer)
[![CircleCI](https://circleci.com/gh/shopizer-ecommerce/shopizer.svg?style=svg)](https://circleci.com/gh/shopizer-ecommerce/shopizer)


Java open source e-commerce software

Headless commerce and Rest api for ecommerce

- Catalog
- Shopping cart
- Checkout
- Merchant
- Order
- Customer
- User


Shopizer Headless commerce consists of the following components:

- Spring boot Java / Spring boot backend
- Angular administration web application
- React JS front end application

Prerequisites

Before you begin, ensure you have the following installed on your system:

Java 11+ (tested with Java 11, 16, and 17)

Maven (for building the project)

Docker (optional, for containerized deployment)

See the demo: [**New demo on the way 2023]
-------------------
Headless demo Available soon

1.  Run from Docker images:

From the command line:

```
docker run -p 8080:8080 shopizerecomm/shopizer:latest
```
       
2. Run the administration tool

⋅⋅⋅ Requires the java backend to be running

```
docker run \
 -e "APP_BASE_URL=http://localhost:8080/api" \
 -p 82:80 shopizerecomm/shopizer-admin
```


3. Run React Shop Sample Site

⋅⋅⋅ Requires the Java backend to be running.

- **Reason**: The React Shop sample site interacts with the backend API to retrieve and display products, handle customer authentication, and process orders. Without the backend running, the frontend will not be able to fetch necessary data or execute actions.

- **Steps**:
   1. Ensure the backend is running using the steps outlined in the previous section.
   2. Run the following Docker command to start the React Shop sample site:
      ```bash
      docker run \
      -e "APP_MERCHANT=DEFAULT" \
      -e "APP_BASE_URL=http://localhost:8080" \
      -p 80:80 shopizerecomm/shopizer-shop-reactjs
      ```

- **Access**: Once the site is running, you can access it at [http://localhost:80](http://localhost:80).

- **Troubleshooting**: If the site does not load properly, check:
  1. That the backend service is running without errors.
  2. That there are no network conflicts on port `80` or `8080`.


API documentation:
-------------------

https://app.swaggerhub.com/apis-docs/shopizer/shopizer-rest-api/3.0.1#/

Get the source code:
-------------------
Clone the repository:
     
	 $ git clone git://github.com/shopizer-ecommerce/shopizer.git
	 
	 $ git clone git://github.com/shopizer-ecommerce/shopizer-admin.git
	 
	 $ git clone git://github.com/shopizer-ecommerce/shopizer-shop-reactjs.git

If this is your first time using Github, review http://help.github.com to learn the basics.

You can also download the zip file containing the code from https://github.com/shopizer-ecommerce for each of the the projects above

To build the application:
-------------------

1. Shopizer backend


From the command line:

	$ cd shopizer
	$ mvnw clean install
	$ cd sm-shop
	$ mvnw spring-boot:run

2. Shopizer admin

Form compiling and running Shopizer admin consult the repo README file

3. Shop sample site

Form compiling and running Shopizer admin consult the repo README file


### Access the application:
-------------------

Access the headless web application at: http://localhost:8080/swagger-ui.html


The instructions above will let you run the application with default settings and configurations.
Please read the instructions on how to connect to MySQL, configure an email server and configure other subsystems


### Documentation:
-------------------

### Documentation and Resources

1. **Official Documentation**:
   - **Description**: Comprehensive guide covering setup, configuration, and advanced usage of Shopizer.
   - **Link**: [Shopizer Documentation](https://shopizer-ecommerce.github.io/documentation/)

2. **API Documentation**:
   - **Description**: Explore all available API endpoints with examples and detailed descriptions. Ideal for developers integrating Shopizer into their applications.
   - **Link**: [Shopizer API Documentation](https://app.swaggerhub.com/apis-docs/shopizer/shopizer-rest-api/3.0.1#/)

3. **Community and Support**:
   - **Slack Channel**:
     - **Description**: Join the Shopizer community on Slack to ask questions, share feedback, and get support from other users and developers.
     - **Link**: [Join Slack Channel](https://communityinviter.com/apps/shopizer/shopizer)
   - **ChatOps**:
     - **Description**: Direct link to the Shopizer workspace on Slack for real-time discussions.
     - **Link**: [Shopizer Slack Workspace](https://shopizer.slack.com)

4. **Official Website**:
   - **Description**: Learn more about Shopizer, including news, updates, and additional resources.
   - **Link**: [Shopizer Website](http://www.shopizer.com)


### Participation:
-------------------

If you have interest in giving feedback or for participating to Shopizer project in any way
Feel to use the contact form <http://www.shopizer.com/contact.html> and share your email address
so we can send an invite to our Slack channel

### How to Contribute:
-------------------
Contributing to Shopizer is simple and rewarding. Follow the steps below to get started:

1. **Fork the Repository**:
   - **Description**: Create your own copy of the repository on your GitHub account by clicking the **Fork** button at the top-right of the repository page.
   - **Why**: This ensures your changes don’t directly affect the original repository.

2. **Clone from Forked Repository**:
   - **Description**: Download the forked repository to your local machine to start making changes.
   - **Command**:
     ```bash
     git clone https://github.com/yourusername/shopizer.git
     cd shopizer
     ```

3. **Build the Application**:
   - **Description**: Ensure that the application builds successfully and is ready for development or testing.
   - **Steps**:
     - Follow the [Build Steps](#installation-guide) provided earlier in this README.

4. **Synchronize with the Upstream Repository**:
   - **Description**: Keep your fork updated with the latest changes from the main repository.
   - **Commands**:
     ```bash
     git remote add upstream https://github.com/shopizer-ecommerce/shopizer.git
     git pull upstream 3.2.5
     ```
   - **Why**: Synchronizing ensures your changes are compatible with the latest version of the project.

5. **Create a New Branch**:
   - **Description**: Always create a new branch for your feature or fix to keep your work organized.
   - **Command**:
     ```bash
     git checkout -b branch-name
     ```
   - **Tip**: Use descriptive branch names like `feature-add-product-filter` or `fix-cart-bug`.

6. **Make Your Changes**:
   - **Description**: Implement the feature, fix the bug, or update the documentation.

7. **Test Your Changes**:
   - **Description**: Run the application locally and ensure your changes work as intended without breaking existing functionality.
   - **Tip**: Use tools like Swagger UI to test API changes.

8. **Push Changes to Your Fork**:
   - **Description**: Push your changes to your forked repository on GitHub.
   - **Command**:
     ```bash
     git push origin branch-name
     ```

9. **Submit a Pull Request (PR)**:
   - **Description**: Request to merge your changes into the main repository.
   - **Steps**:
     - Navigate to your forked repository on GitHub.
     - Click the **Compare & Pull Request** button.
     - Add a clear title and description explaining your changes.
   - **Tip**: Mention any related issues (e.g., \"Fixes #123\") in the description.

10. **Respond to Feedback**:
    - **Description**: Be prepared to make updates or revisions based on feedback from maintainers.
    - **Why**: Collaboration ensures your contribution meets project standards.

---


