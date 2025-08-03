#!/bin/bash
clear
echo "==============================================="
echo "Testing WEBFLUX approach"
echo "==============================================="

# Light load test
#echo "Light load (50 connections, 30 seconds):"
#wrk -t4 -c50 -d30s --timeout 10s "http://localhost:8080/api/webflux"
#
#echo ""
#echo "Medium load (100 connections, 30 seconds):"
#wrk -t8 -c100 -d30s --timeout 10s "http://localhost:8080/api/webflux"


#echo "Heavy load (200 connections, 20 seconds):"
wrk -t10 -c100 -d30s "http://localhost:8080/api/webflux"
