#!/bin/bash
echo "Starting comprehensive load testing..."
echo "Make sure your application is running on localhost:8080"
echo ""

# Function to check if server is running
check_server() {
    if ! curl -s "http://localhost:8080/api/blocking/rate?base=USD&target=EUR" > /dev/null; then
        echo "❌ Server is not running on localhost:8080"
        echo "Please start your Spring Boot application first"
        exit 1
    fi
    echo "✅ Server is running"
}

# Function to wait between tests
wait_between_tests() {
    echo ""
    echo "Waiting 10 seconds before next test..."
    sleep 10
    echo ""
}

check_server

# Run blocking tests
./test_blocking.sh
wait_between_tests

# Run coroutines tests
./test_coroutines.sh
wait_between_tests

# Run webflux tests
./test_webflux.sh

echo ""
echo "All tests completed!"
