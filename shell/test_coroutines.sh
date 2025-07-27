#!/bin/bash
clear
echo "==============================================="
echo "Testing COROUTINES approach"
echo "==============================================="

# Light load test
echo "Light load (200 connections, 20 seconds):"
wrk -t12 -c200 -d20s --timeout 10s "http://localhost:8080/api/coroutines/rate"

#echo ""
#echo "Medium load (100 connections, 30 seconds):"
#wrk -t8 -c100 -d30s --timeout 10s "http://localhost:8080/api/coroutines/rate"

#echo ""
#echo "Heavy load (400 connections, 30 seconds):"
#wrk -t12 -c400 -d30s --timeout 10s "http://localhost:8080/api/coroutines/rate"
