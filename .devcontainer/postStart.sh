#!/bin/bash

tar -xzf /workspace/microsoft-autodev-0.5.0.tgz -C /workspace/
cd /workspace/package
npm run start:server
