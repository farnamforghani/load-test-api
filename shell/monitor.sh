#!/bin/bash
echo "Monitoring application stats (Ctrl+C to stop)..."
while true; do
    clear
    echo "=== Application Stats at $(date) ==="
    curl -s "http://localhost:8080/api/monitor/stats" | python3 -m json.tool
    sleep 2
done
