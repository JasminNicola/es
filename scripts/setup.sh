# scripts/setup.sh
#!/bin/bash

# 1. Ollama installieren (falls nicht vorhanden)
if ! command -v ollama &> /dev/null; then
    echo "Installiere Ollama..."
    curl -fsSL https://ollama.com/install.sh | sh
else
    echo "Ollama bereits installiert"
fi

# 2. Modell laden
echo "Lade Modell..."
ollama pull llama3.2

# 3. Ollama starten
ollama serve &
echo "Ollama läuft auf http://localhost:11434"


