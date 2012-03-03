# Firewall with iptables.
# Copyright (C) 2008,2009,2012 Jefferson Campos - foguinho [dot] peruca [at] gmail [dot] com

# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.

# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.

# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

#!/bin/bash

show_usage()
{
    echo ""
    echo "firewall Copyright (C) 2008,2009,2012 Jefferson Campos"
    echo "This program comes with ABSOLUTELY NO WARRANTY;"
    echo "This is free software, and you are welcome to redistribute it under certain conditions;"
    echo "usage: ./firewall.sh [START|STOP|RESTART]"
    echo ""
}

start(){
################################ ESSENTIAL ##################################

# load module for iptable    
    modprobe iptable_nat

# no limits for local 
#    iptables -A INPUT -i eth0 -j ACCEPT

# interface loopback - essential for server!!
    iptables -A INPUT -i lo -j ACCEPT

# packages, from connection that is already initialized, will be accepted
    iptables -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT


########################## ATTACK'S DEFENSE ###############################

# Just 1 ping is allowed - avoid DoS attack.
    iptables -A INPUT -p icmp --icmp-type echo-request -m limit --limit 1/s -j ACCEPT

# No broadcast ping - avoid DoS attack
    echo > 1 /proc/sys/net/ipv4/icmp_echo_ignore_broadcasts

# Active 'source routing' - an attacker can't impersonate by a authorized server.
    echo > 0 /proc/sys/net/ipv4/conf/all/accept_source_route

# Avoid SYN Flood attack
    echo > 1 /proc/sys/net/ipv4/tcp_syncookies

# Answer will be in same interface that is already initialized.
    echo > 1 /proc/sys/net/ipv4/conf/default/rp_filter

# Drop bad packages
    iptables -A INPUT -m state --state INVALID -j DROP

# Avoid IP spoofing
    echo 1 > /proc/sys/net/ipv4/conf/default/rp_filter


################################ CLOSE ALL REST ##################################

	# Fecha todas as outras portas !!! - TCP
    iptables -A INPUT -p tcp --syn -j DROP

	# Bloqueia as portas UDP de 0 a 1023 (com excecao as abertas acima):
    iptables -A INPUT -p udp --dport 0:1023 -j DROP

    echo "Firewall LOADED successfully!!!"
}

stop()
{
    iptables -F
    iptables -t nat -F
    iptables -P INPUT ACCEPT
    iptables -P OUTPUT ACCEPT
    echo 0 > /proc/sys/net/ipv4/ip_forward
    echo "Firewall UNLOADED successfully!!!"
}


case $1 in 
    "start") start;;
    "stop") stop;;
    "restart") stop; start;;
    *) echo show_usage;;
esac