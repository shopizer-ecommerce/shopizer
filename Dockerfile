# Demo Dockerfile for CI/CD demonstration
# Extends the official Shopizer image with custom modifications

FROM shopizerecomm/shopizer:latest

USER root

# Add custom configuration files that can be modified for CI/CD demo
# This file can be changed to trigger CI/CD pipeline
# COPY custom-config.properties /path/to/config/

# Add a version file that shows when this was built (for demo purposes)
RUN echo "Build Date: $(date)" > /build-info.txt && \
    echo "Custom Build Version: 3.2.5-CI/CD-DEPLOYMENT" >> /build-info.txt && \
    echo "==================================" >> /build-info.txt && \
    echo "CI/CD PIPELINE AUTO-DEPLOYMENT ACTIVE!" >> /build-info.txt && \
    echo "==================================" >> /build-info.txt

# Set environment variables if needed
ENV SPRING_PROFILES_ACTIVE=cloud

# You can add custom modules, configurations, or modifications here
# For example: COPY custom-modules/*.jar /opt/app/

# The rest inherits from the base image