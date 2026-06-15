# scripts/setup.ps1
# Ollama installieren
if (-not (Get-Command ollama -ErrorAction SilentlyContinue)) {
    Write-Host "Installiere Ollama..."
    winget install Ollama.Ollama
}

# Modell laden
ollama pull llama3.2

# Starten
Start-Process ollama -ArgumentList "serve"
Write-Host "Ollama läuft auf http://localhost:11434"
