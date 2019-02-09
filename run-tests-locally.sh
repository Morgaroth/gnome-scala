#!/usr/bin/env bash

~/gitlab-runner-linux-amd64 exec docker tests \
    --cache-dir=/gitlab-cache \
    --docker-cache-dir=/tmp/gitlab-cache \
    --docker-volumes=/tmp/gitlab-cache