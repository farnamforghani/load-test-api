#!/bin/bash
clear

echo "==============================================="
echo "Testing BLOCKING approach with WebClient"
echo "==============================================="

# Light load test
#echo "Light load (200 connections, 20 seconds):"
wrk -t10 -c100 -d30s "http://localhost:8080/api/blocking/webclient"

#
#echo ""
#echo "Medium load (100 connections, 30 seconds):"
#wrk -t8 -c100 -d30s --timeout 10s "http://localhost:8080/api/blocking/webclient"
#
#echo ""
#echo "Heavy load (200 connections, 30 seconds):"
#wrk -t12 -c200 -d30s --timeout 10s "http://localhost:8080/api/blocking/webclient"
