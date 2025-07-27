#!/bin/bash
clear

echo "==============================================="
echo "Testing BLOCKING approach"
echo "==============================================="

# Light load test
#echo ""
#echo "Light load (50 connections, 30 seconds):"
#wrk -t12 -c400 -d30s --timeout 10s "http://localhost:8080/api/blocking/rate?base=USD&target=EUR"
#
#echo ""
#echo "Medium load (100 connections, 30 seconds):"
#wrk -t12 -c800 -d30s --timeout 10s "http://localhost:8080/api/blocking/rate?base=USD&target=EUR"

#echo ""
#echo "Heavy load (400 connections, 30 seconds):"
#wrk -t12 -c400 -d30s --timeout 10s "http://localhost:8080/api/blocking/rate"
#wrk -t12 -c200 -d30s --timeout 10s "http://localhost:8080/api/blocking/rate?base=USD&target=EUR"


# Light load test
echo "Light load (200 connections, 20 seconds):"
wrk -t12 -c200 -d20s --timeout 10s "http://localhost:8080/api/blocking/rate"

#
#echo ""
#echo "Medium load (100 connections, 30 seconds):"
#wrk -t8 -c100 -d30s --timeout 10s "http://localhost:8080/api/blocking/rate"
#
#echo ""
#echo "Heavy load (200 connections, 30 seconds):"
#wrk -t12 -c200 -d30s --timeout 10s "http://localhost:8080/api/blocking/rate"
