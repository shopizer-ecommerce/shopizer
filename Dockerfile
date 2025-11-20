# Demo Dockerfile for CI/CD demonstration
# Uses your custom Shopizer image from ECR

FROM 550487074451.dkr.ecr.us-east-1.amazonaws.com/shopizer-demo-backend:latest

USER root

# Add a version file that shows when this was built (for demo purposes)
RUN echo "Build Date: $(date)" > /build-info.txt && \
    echo "Custom Build Version: 3.2.5-CI/CD-DEPLOYMENT" >> /build-info.txt && \
    echo "==================================" >> /build-info.txt && \
    echo "CI/CD PIPELINE AUTO-DEPLOYMENT ACTIVE!" >> /build-info.txt && \
    echo "==================================" >> /build-info.txt

# Set environment variables if needed
ENV SPRING_PROFILES_ACTIVE=cloud

# The rest inherits from your custom image